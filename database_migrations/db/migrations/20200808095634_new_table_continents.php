<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableContinents extends AbstractMigration
{

    public function change(): void
    {
        $continents = $this->table('continents', ['signed' => false]);
        $continents->addColumn('title', 'string', ['limit' => 80])
        ->addColumn('code', 'string', ['limit' => 2])
        ->addIndex(['code'], ['unique' => true])
        ->create();
    }
}
