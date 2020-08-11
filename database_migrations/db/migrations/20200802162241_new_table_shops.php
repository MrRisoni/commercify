<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShops extends AbstractMigration
{

    public function change(): void
    {
        $shops = $this->table('shops', ['signed' => false]);
        $shops->addColumn('title', 'string', ['limit' => 55])
            ->addColumn('owner_id', 'biginteger', ['signed' => false])
            ->addForeignKey('owner_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addColumn('created', 'datetime')
            ->create();
    }
}
