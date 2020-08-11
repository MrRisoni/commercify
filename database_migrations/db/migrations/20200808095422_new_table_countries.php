<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableCountries extends AbstractMigration
{

    public function change(): void
    {
        $countries = $this->table('countries', ['signed' => false]);
        $countries->addColumn('title', 'string', ['limit' => 80])
        ->addColumn('code', 'string', ['limit' => 2])
        ->addColumn('continent', 'string', ['limit' => 2])
        ->addIndex(['code'], ['unique' => true])
        ->create();
    }
}
