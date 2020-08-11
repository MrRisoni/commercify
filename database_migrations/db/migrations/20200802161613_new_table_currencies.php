<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableCurrencies extends AbstractMigration
{

    public function change(): void
    {
        $currencies = $this->table('currencies', ['signed' => false]);
        $currencies->addColumn('title', 'string', ['limit' => 55])
            ->addColumn('code', 'string', ['limit' => 3])
            ->addColumn('rate', 'decimal', ['precision' => 5,'scale'=>2])
            ->addIndex(['code'], ['unique' => true])
            ->create();
    }
}
